package com.challenge.ml.bsn;

import javax.transaction.Transactional;

import org.jvnet.hk2.annotations.Service;
import org.modelmapper.ModelMapper;


@Service
@Transactional
public class EnrolamientoBsnImpl implements EnrolamientoBsn {
	


	final private ModelMapper mapper = new ModelMapper();
	


}
