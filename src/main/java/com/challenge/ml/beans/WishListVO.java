package com.challenge.ml.beans;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListVO {

	private int idWishList;
	private List<BookVO> book= new ArrayList<>();
	private int idUser;

}
