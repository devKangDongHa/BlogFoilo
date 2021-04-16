package com.project.PortFolio.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PortFolioDAO implements InterPortFolioDAO {
	
	@Autowired
	private SqlSessionTemplate sqlsession;

}
