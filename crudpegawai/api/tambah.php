<?php
header('Content-Type: application/json');
include dirname(dirname(__FILE__)).'\api\db\Db.class.php';
$db = new Db();
$nama_pegawai = isset($_POST['nama_pegawai']) ? $_POST['nama_pegawai'] : '';
$tanggalLahir = isset($_POST['tanggalLahir']) ? $_POST['tanggalLahir'] : '';
$devisi = isset($_POST['devisi']) ? $_POST['devisi'] : '';
if (empty($nama_pegawai)) {
    $arr = array();
    $arr['status'] = 'error';
    echo json_encode($arr);
    exit();
}
$datas = array();
$datas['nama_pegawai'] = $nama_pegawai;
$datas['tanggalLahir'] = $tanggalLahir;
$datas['devisi'] = $devisi;
$result = $db->insert('tbl_pegawai', $datas);
if (!$result) {
    $arr = array();
    $arr['status'] = 'error';
    echo json_encode($arr);
}
else{
	$arr = array();
    $arr['status'] = 'success';
    echo json_encode($arr);
}