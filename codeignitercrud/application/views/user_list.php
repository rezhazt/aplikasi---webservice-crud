<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Simple data karyawan</title>
	
</head>
<body>
<div class="container">
	<h1 class="page-header text-center">Simple data karyawan</h1>
	<div class="row">
		<div class="col-sm-8 col-sm-offset-2">
			<a href="http://localhost/codeignitercrud/index.php/users/addnew" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> Add New</a><br><br>
			<table class="table table-bordered table-striped">
				<thead>
					<tr>
						<th>ID</th>
						<th>nama_pegawai</th>
						<th>tanggalLahir</th>
						<th>devisi</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<?php
					foreach($users as $user){
						?>
						<tr>
							<td><?php echo $user->id; ?></td>
							<td><?php echo $user->nama_pegawai; ?></td>
							<td><?php echo $user->tanggalLahir; ?></td>
							<td><?php echo $user->devisi; ?></td>
							<td><a href="http://localhost/codeignitercrud/index.php/users/edit/<?php echo $user->id; 
							?>" class="btn btn-success"><span class="glyphicon glyphicon-edit"></span> Edit</a> ||
							<a href="http://localhost/codeignitercrud/index.php/users/delete/<?php echo $user->id; ?>" class="btn btn-danger">
							<span class="glyphicon glyphicon-trash"> </span> Delete</a></td>
						</tr>
						<?php
					}
					?>
				</tbody>
			</table>
		</div>
	</div>
</div>
</body>
</html>