package com.challenge.ml.bsn;

import com.challenge.ml.beans.GoogleBooksVO;

public interface GoogleBooksBsn {
	/**
	 * Method to create request.
	 *
	 * @param googleBooksVO Object with the parameter for request.
	 *
	 * @return String request generated.
	 */
	StringBuilder createRequest(GoogleBooksVO googleBooksVO);


}
