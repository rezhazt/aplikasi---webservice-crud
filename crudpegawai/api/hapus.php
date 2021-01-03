<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'\api\db\Db.class.php';
$db = new Db();
$id = isset($_GET['id']) ? (int) $_GET['id'] : '';
if (empty($id)) {
    $arr = array();
    $arr['status'] = 'error';
    echo json_encode($arr);
    exit();
}
$result=$db->query('delete from tbl_pegawai where id='.$id);
if(!$result){
	$arr = array();
    $arr['status'] = 'error';
    echo json_encode($arr);
}
else{
	$arr = array();
    $arr['status'] = 'success';
    echo json_encode($arr);
}