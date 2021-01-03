<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'/db/Db.class.php';
$db = new Db();
$id = isset($_POST['id']) ? (int) $_POST['id'] : '';
$nama_pegawai = isset($_POST['nama_pegawai']) ? $_POST['nama_pegawai'] : '';
$tanggalLahir = isset($_POST['tanggalLahir']) ? $_POST['tanggalLahir'] : '';
$devisi = isset($_POST['devisi']) ? $_POST['devisi'] : '';
if (empty($id) OR empty($nama_pegawai)) {
    $arr = array();
    // $arr['info'] = 'error';
    // $arr['msg'] = 'ID atau nama Kategori tidak ada';
    echo json_encode($arr);
    exit();
}
$datas = array();
$datas['nama_pegawai'] = $nama_pegawai;
$datas['tanggalLahir'] = $tanggalLahir;
$datas['devisi'] = $devisi;
$exec = $db->update('tbl_pegawai', $datas,' where id='.$id);
if (!$exec) {
    $arr = array();
    // $arr['info'] = 'error';
    // $arr['msg'] = 'Query tidak berhasil dijalankan.';
    echo json_encode($arr);
    exit();
}
$arr = array();
// $arr['info'] = 'success';
// $arr['msg'] = 'Data berhasil diproses.';
echo json_encode($arr);