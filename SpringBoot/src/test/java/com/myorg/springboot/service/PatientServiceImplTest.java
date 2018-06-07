package com.myorg.springboot.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.myorg.springboot.model.Institution;
import com.myorg.springboot.model.Patient;
import com.myorg.springboot.repositories.PatientRepository;
import com.myorg.springboot.service.PatientService;
import com.myorg.springboot.service.PatientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceImplTest {

	@Mock
	PatientRepository mockPatientRepository;

	@InjectMocks
	PatientServiceImpl patientService;

	private Patient patient;
	
	private List<Patient> patients;
	
	private Long patientId = 1L;
	
	@Before
	public void setUp() throws Exception {
		patient = new Patient();
		patient.setId(patientId);
		patient.setName("sharath");
		
		patients = new ArrayList<Patient>();
		patients.add(patient);
	}

	@Test
	public void testFindById()
			throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {

		Mockito.when(mockPatientRepository.findOne(Mockito.anyLong())).thenReturn(patient);
		Patient patValue = patientService.findById(1L);
		assertEquals(patientId, patValue.getId());
		assertEquals("sharath", patValue.getName());
		Mockito.verify(mockPatientRepository).findOne(Mockito.anyLong());
	};

	@Test
	public void findByInstitution() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		Mockito.when(mockPatientRepository.findByInstitution(Mockito.any(Institution.class))).thenReturn(patients);
		List<Patient> patValues = patientService.findByInstitution(Mockito.any(Institution.class));
		Assert.assertNotNull(patValues);
		assertEquals(patientId, patValues.get(0).getId());
		assertEquals("sharath", patValues.get(0).getName());
		Mockito.verify(mockPatientRepository).findByInstitution(Mockito.any(Institution.class));
		
	}

	@Test
	public void testGetPatientAge() {

		Calendar today = Calendar.getInstance();
		Calendar prevYearToday = today;
		prevYearToday.add(Calendar.MONTH, -25);
		PatientService patientService = new PatientServiceImpl();
		Long dob = prevYearToday.getTimeInMillis();
		Integer age = patientService.getPatientAge(dob);
		assertEquals(Integer.valueOf(2), age);

	};

}