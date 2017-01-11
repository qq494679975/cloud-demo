package com.cwd.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.HbaseUtils;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/21.
 */
public class HBaseUtil implements Serializable {

    private static final long serialVersionUID = -8050334695185663581L;

    private static final String QUORUM = "master:2181,slave1:2181,slave2:2181";
    private static final String CLIENTPORT = "2181";
    private static final String HBASEMASTER = "192.168.116.240:60000";
    private static Configuration conf = null;
    private static HbaseTemplate hbaseTemplate;

    static {
        try {
            conf = HBaseConfiguration.create();
            conf.set("hbase.master", HBASEMASTER);
            conf.set("hbase.zookeeper.quorum", QUORUM);
            conf.set("hbase.zookeeper.property.clientPort", CLIENTPORT);
            hbaseTemplate = new HbaseTemplate();
            hbaseTemplate.setConfiguration(conf);
            hbaseTemplate.setAutoFlush(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws IOException {
        return ConnectionFactory.createConnection(hbaseTemplate.getConfiguration());
    }

    /**
     * 判断表是否存在
     */
    public boolean isTableExists(String tableName) throws Exception {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();
        boolean ex = admin.tableExists(TableName.valueOf(tableName));

        admin.close();
        connection.close();

        return ex;
    }

    /**
     * 创建表结构
     */
    public void createTable(String tableName, String... columnFamilies) throws Exception {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        if (!admin.tableExists(TableName.valueOf(tableName))) {
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            for (String fc : columnFamilies) {
                tableDescriptor.addFamily(new HColumnDescriptor(fc));
            }

            admin.createTable(tableDescriptor);
        }

        admin.close();
        connection.close();
    }

    /**
     * 删除表结构
     */
    public void dropTable(String tableName) throws Exception {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        try {
            admin.disableTable(TableName.valueOf(tableName));
            admin.deleteTable(TableName.valueOf(tableName));
        } finally {
            admin.close();
            connection.close();
        }
    }

    /**
     * 模糊获取表名列表
     */
    public List<String> getTableList(String regex, boolean includeSysTables) throws Exception {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        TableName[] tableNames;
        if (regex == null || regex.length() == 0) {
            tableNames = admin.listTableNames();
        } else {
            tableNames = admin.listTableNames(regex, includeSysTables);
        }

        List<String> tables = new ArrayList<>();
        for (TableName tableName : tableNames) {
            tables.add(tableName.getNameAsString());
        }

        admin.close();
        connection.close();

        return tables;
    }

    /**
     * 新增数据
     */
    public void insertRecord(String tableName, String rowKey, String family, Object[] columns, Object[] values) throws Exception {
        hbaseTemplate.execute(tableName, new TableCallback<Object>() {

            public Object doInTable(HTableInterface htable) throws Throwable {
                Put put = new Put(Bytes.toBytes(rowKey));
                for (int j = 0; j < columns.length; j++) {
                    //为空字段不保存
                    if (values[j] != null) {
                        String column = String.valueOf(columns[j]);
                        String value = String.valueOf(values[j]);
                        put.addColumn(Bytes.toBytes(family),
                                Bytes.toBytes(column),
                                Bytes.toBytes(value));
                    }
                }

                htable.put(put);

                return null;
            }
        });
    }

    /**
     * 批量新增数据
     */
    String batchTableName;
    boolean synchronizeMode;
    Map<String, Put> batchPuts = new HashMap<>();

    public void beginBatchInsert(String tableName, boolean synchronizeMode) throws Exception {
        this.batchTableName = tableName;
        this.synchronizeMode = synchronizeMode;
        this.batchPuts.clear();
    }

    public void batchInsert(String rowKey, String family, Object[] columns, Object[] values) throws Exception {
        Put put = batchPuts.get(rowKey);
        if (put == null) {
            put = new Put(Bytes.toBytes(rowKey));
            batchPuts.put(rowKey, put);
        }

        for (int i = 0; i < columns.length; ++i) {
            put.addColumn(Bytes.toBytes(family),
                    Bytes.toBytes(String.valueOf(columns[i])),
                    Bytes.toBytes(String.valueOf(values[i])));
        }
    }

    public void endBatchInsert() throws Exception {
        hbaseTemplate.execute(batchTableName, new TableCallback<Object>() {

            public Object doInTable(HTableInterface table) throws Throwable {
                Object[] results = null;

                List<Put> puts = new ArrayList<>(batchPuts.values());
                if (synchronizeMode) {
                    table.put(puts);
                } else {
                    table.batch(puts, results);
                }

                batchTableName = "";
                batchPuts.clear();

                return null;
            }
        });
    }

    /**
     * 修改数据
     */
    public void updateRecord(String tableName, String rowKey, String familyName, String columnName, String value) throws Exception {
        hbaseTemplate.execute(tableName, new TableCallback<Object>() {

            public Object doInTable(HTableInterface table) throws Throwable {
                Put put = new Put(Bytes.toBytes(rowKey));
                put.addColumn(Bytes.toBytes(familyName), Bytes.toBytes(columnName), Bytes.toBytes(value));
                table.put(put);

                return null;
            }
        });
    }

    /**
     * 删除数据
     */
    public void deleteRecord(String tableName, String rowKey) throws Exception {
        hbaseTemplate.execute(tableName, new TableCallback<Object>() {

            public Object doInTable(HTableInterface table) throws Throwable {
                Delete deleteAll = new Delete(Bytes.toBytes(rowKey));

                table.delete(deleteAll);
                return null;
            }
        });
    }

    /**
     * 批量删除数据
     */
    public Object[] deleteRecords(String tableName, String[] rowKeys) throws Exception {
        return hbaseTemplate.execute(tableName, new TableCallback<Object[]>() {

            public Object[] doInTable(HTableInterface table) throws Throwable {
                List<Delete> deletes = new ArrayList<>(rowKeys.length);
                for (String rowKey : rowKeys) {
                    Delete delete = new Delete(Bytes.toBytes(rowKey));
                    deletes.add(delete);
                }

                Object[] results = new Object[deletes.size()];
                table.batch(deletes, results);

                return results;
            }
        });
    }

    /**
     * 删除列族
     *
     * @param tableName
     * @param rowKey
     * @param familyName
     * @throws IOException
     */
    public void deleteFamily(String tableName, String rowKey, String familyName) throws Exception {
        hbaseTemplate.delete(tableName, rowKey, familyName);
    }

    /**
     * 删除某列
     */
    public void deleteColumn(String tableName, String rowKey, String familyName, String columnName) throws Exception {
        hbaseTemplate.delete(tableName, rowKey, familyName, columnName);
    }

    /**
     * 清空表数据
     */
    public void truncate(List<String> tables) throws Exception {
        Connection connection = getConnection();
        Admin admin = connection.getAdmin();

        try {
            for (String tableName : tables) {
                TableName tn = TableName.valueOf(tableName);
                if (admin.tableExists(TableName.valueOf(tableName))) {
                    HTableDescriptor descriptor = admin.getTableDescriptor(tn);
                    admin.disableTable(tn);
                    admin.deleteTable(tn);
                    admin.createTable(descriptor);
                }
            }
        } finally {
            admin.close();
            connection.close();
        }
    }

    /********************** 通过rowkey查询操作 *********************************************/

    /**
     * 通过rowkey获取某行数据
     */
    public Map<String, Object> getResultMap(String tableName, String rowKey) throws Exception {
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<Map<String, Object>>() {
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {
                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("rowkey", rowKey);
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        //默认不加列族
                        // Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()) +"_"
                        map.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),
                                Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
                    }
                }
                return map;
            }
        });
    }

    /**
     * 通过rowkey获取多行数据
     */
    public List<Map<String, Object>> getResultMapList(String tableName, String startRow, String stopRow) {
        Scan scan = new Scan();
        if (startRow == null) {
            startRow = "";
        }
        if (stopRow == null) {
            stopRow = "";
        }
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        return hbaseTemplate.find(tableName, scan, new RowMapper<Map<String, Object>>() {
            public Map<String, Object> mapRow(Result result, int rowNum) throws Exception {

                List<Cell> ceList = result.listCells();
                Map<String, Object> map = new HashMap<String, Object>();
                Map<String, Map<String, Object>> returnMap = new HashMap<String, Map<String, Object>>();
                String row = "";
                if (ceList != null && ceList.size() > 0) {
                    for (Cell cell : ceList) {
                        row = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                        String value = Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
                        //默认不加列族
                        //String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                        map.put(quali, value);
                    }
                    map.put("row", row);
                }
                return map;
            }
        });
    }

    /**
     * 通过rowkey获取某行数据
     */
    public Result getResult(String tableName, String rowKey) throws Exception {
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<Result>() {

            public Result mapRow(Result result, int rowNum) throws Exception {
                return result;
            }
        });
    }

    /**
     * 通过rowkey获取多行数据
     */
    public Result[] getResultList(String tableName, List<String> rowKeys) throws Exception {
        return hbaseTemplate.execute(tableName, new TableCallback<Result[]>() {

            public Result[] doInTable(HTableInterface table) throws Throwable {
                List<Get> list = new ArrayList<Get>();
                for (String rowKey : rowKeys) {
                    Get get = new Get(Bytes.toBytes(rowKey));
                    list.add(get);
                }
                return table.get(list);
            }
        });
    }


    /**
     * 通过rowKey获取某列值
     */
    public String getCell(String tableName, String rowKey, String familyName, String columnName) throws Exception {
        String value = hbaseTemplate.get(tableName, rowKey, familyName, columnName, new RowMapper<String>() {

            public String mapRow(Result result, int rowNum) throws Exception {
                return Bytes.toString(result.value());
            }
        });

        return value;
    }

    /*********************** HBase查询操作 ****************************************************/
    /**
     * HBase条件查询
     */
    public ResultScanner getScanner(String tableName, Scan scan) {
        return hbaseTemplate.execute(tableName, new TableCallback<ResultScanner>() {

            public ResultScanner doInTable(HTableInterface hTableInterface) throws Throwable {
                ResultScanner resultScanner = hTableInterface.getScanner(scan);

                return resultScanner;
            }
        });
    }

    public static void main(String[] args) {
        HBaseUtil hbaseUtil = new HBaseUtil();
        try {
            Object[] colum = new Object[]{"testColum1", "testColum2"};
            Object[] value = new Object[]{"testValue1", "testValue2"};
            hbaseUtil.insertRecord("flumeLog", "test1", "flumeLogFamily", colum, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
