<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'/db/Db.class.php';
$db = new Db();
$nama_pegawai = isset($_POST['nama_pegawai']) ? $_POST['nama_pegawai'] : '';
$tanggalLahir = isset($_POST['tanggalLahir']) ? $_POST['tanggalLahir'] : '';
$devisi = isset($_POST['devisi']) ? $_POST['devisi'] : '';
if (empty($nama_pegawai)) {
    $arr = array();
    // $arr['info'] = 'error';
    // $arr['msg'] = 'Kategori tidak ada';
    echo json_encode($arr);
    exit();
}
$datas = array();
$datas['nama_pegawai'] = $nama_pegawai;
$datas['tanggalLahir'] = $tanggal;
$datas['devisi'] = $devisi
$exec = $db->insert('tbl_pegawai', $datas);
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