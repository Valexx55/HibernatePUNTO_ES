package edu.val.service;

import java.util.List;

public class EmpleadoBasic {
	
	private int employeeId;
	private String firstName;
	
	
	public int getId() {
		return employeeId;
	}
	public void setId(int id) {
		this.employeeId = id;
	}
	public String getNombre() {
		return firstName;
	}
	public void setNombre(String nombre) {
		this.firstName = nombre;
	}
	public EmpleadoBasic(int employeeId, String firstName) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
	}
	
	
	public static void mostrarEmpleados (List<EmpleadoBasic> le)
	{
		for (EmpleadoBasic empleadoBasic : le) {
			System.out.println(empleadoBasic.employeeId + " " + empleadoBasic.firstName);
		}
		
	}	
}