<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Simple data karyawan</title>
	<link rel="stylesheet" type="text/css" href="<?php echo base_url(); ?>bootstrap/css/bootstrap.min.css">
</head>
<body>
<div class="container">
	<h1 class="page-header text-center">Simple data karyawan</h1>
	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
			<h3>Edit Form
				<span class="pull-right"><a href="<?php echo base_url(); ?>" class="btn btn-primary"><span class="glyphicon glyphicon-arrow-left"></span> Back</a></span>
			</h3>
			<hr>
			<?php extract($user); ?>
			<form method="POST" action="<?php echo base_url(); ?>index.php/users/update/<?php echo $id; ?>">
				<div class="form-group">
					<label>nama_pegawai:</label>
					<input type="text" class="form-control" value="<?php echo $nama_pegawai; ?>" name="nama_pegawai">
				</div>
				<div class="form-group">
					<label>tanggalLahir:</label>
					<input type="date" class="form-control" value="<?php echo $tanggalLahir; ?>" name="tanggalLahir">
				</div>
				<div class="form-group">
					<label>devisi:</label>
					<input type="text" class="form-control" value="<?php echo $devisi; ?>" name="devisi">
				</div>
				<button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-check"></span> Update</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>