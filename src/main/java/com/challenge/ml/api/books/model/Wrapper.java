package com.challenge.ml.api.books.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class Wrapper {
	private int totalItems;
	private ItemsWrapper[] items;



}
