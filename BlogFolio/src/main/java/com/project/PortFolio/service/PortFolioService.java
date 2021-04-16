package com.project.PortFolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.PortFolio.model.InterPortFolioDAO;

@Service
public class PortFolioService implements InterPortFolioService {

	@Autowired
	private InterPortFolioDAO dao;
	
}
