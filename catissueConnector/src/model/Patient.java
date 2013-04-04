package model;

public class Patient {

	String patient_id;
	String vital_status_cd;
	String birth_date;
	String age_in_years_num;
	String race_cd;
	String sex_cd;
	String study_name;
	
	
	@Override
	public String toString() {
		return "Patient [patient_id=" + patient_id + ", vital_status_cd="
				+ vital_status_cd + ", birth_date=" + birth_date
				+ ", age_in_years_num=" + age_in_years_num + ", race_cd="
				+ race_cd + ", sex_cd=" + sex_cd + ", study_name=" + study_name
				+ "]";
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getVital_status_cd() {
		return vital_status_cd;
	}
	public void setVital_status_cd(String vital_status_cd) {
		this.vital_status_cd = vital_status_cd;
	}
	public String getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
	public String getAge_in_years_num() {
		return age_in_years_num;
	}
	public void setAge_in_years_num(String age_in_years_num) {
		this.age_in_years_num = age_in_years_num;
	}
	public String getRace_cd() {
		return race_cd;
	}
	public void setRace_cd(String race_cd) {
		this.race_cd = race_cd;
	}
	public String getSex_cd() {
		return sex_cd;
	}
	public void setSex_cd(String sex_cd) {
		this.sex_cd = sex_cd;
	}
	public String getStudy_name() {
		return study_name;
	}
	public void setStudy_name(String study_name) {
		this.study_name = study_name;
	}
	
	
}
