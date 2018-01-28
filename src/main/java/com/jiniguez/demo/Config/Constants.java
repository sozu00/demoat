package com.jiniguez.demo.Config;

import java.text.SimpleDateFormat;

public final class Constants {

	/**
	 * Para evitar casos en los que se envia un ID null y no es posible buscarlo, se usa esta constante, que nunca debe ser asignada.
	 */
	public static final int NOT_FINDABLE_ID = -1;
	public static SimpleDateFormat DATEFORMAT = new SimpleDateFormat("DD-MM-YYYY");

}
