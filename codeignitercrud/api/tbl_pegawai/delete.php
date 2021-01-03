<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'/db/Db.class.php';
$db = new Db();
$id = isset($_POST['id']) ? (int) $_POST['id'] : '';
if (empty($id)) {
    $arr = array();
    // $arr['info'] = 'error';
    // $arr['msg'] = 'ID Kategori tidak ditemukan';
    echo json_encode($arr);
    exit();
}
$db->query('delete from categories where id='.$id);
$arr = array();
// $arr['info'] = 'success';
// $arr['msg'] = 'Data berhasil dihapus.';
echo json_encode($arr);