<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'/db/Db.class.php';
$db = new Db();
$cat_id = isset($_GET['id']) ? (int) $_GET['id'] : 0;
$detail = $db->row('select * from tbl_pegawai where cat_id='.$cat_id);
$arr = array();
$arr['info'] = 'success';
$arr['result'] = $detail;
echo json_encode($arr);