<?php
	class Users_model extends CI_Model {
		function __construct(){
			parent::__construct();
			$this->load->database();
		}

		public function getAllUsers(){
			$query = $this->db->get('tbl_pegawai');
			return $query->result(); 
		}

		public function insertuser($user){
			return $this->db->insert('tbl_pegawai', $user);
		}

		public function getUser($id){
			$query = $this->db->get_where('tbl_pegawai',array('id'=>$id));
			return $query->row_array();
		}

		public function updateuser($user, $id){
			$this->db->where('tbl_pegawai.id', $id);
			return $this->db->update('tbl_pegawai', $user);
		}

		public function deleteuser($id){
			$this->db->where('tbl_pegawai.id', $id);
			return $this->db->delete('tbl_pegawai');
		}

	}
?>