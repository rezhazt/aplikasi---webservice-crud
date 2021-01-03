<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'\api\db\Db.class.php';
$db = new Db();
$query = $db->query('select * from tbl_pegawai');
$arr = array();
$arr['jumlah_pegawai'] = count($query);
$arr['list_pegawai'] = $query;
echo json_encode($arr);