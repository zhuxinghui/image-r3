package org.rivu.image.solr.function;

import java.util.StringTokenizer;

import org.apache.solr.search.function.DocValues;
import org.apache.solr.search.function.DualFloatFunction;
import org.apache.solr.search.function.ValueSource;

public class RivuImageFunction extends DualFloatFunction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param a
	 *            the numerator.
	 * @param b
	 *            the denominator.
	 */
	public RivuImageFunction(ValueSource a, ValueSource b) {
		super(a, b);
	}

	protected String name() {
		return "rivu";
	}

	protected float func(int doc, DocValues aVals, DocValues bVals) {
		double[] aV = getStringRepresentation(aVals.strVal(doc));
		double[] bV = getStringRepresentation(bVals.strVal(doc));
		double result = 0;
		double Temp1 = 0;
		double Temp2 = 0;

		double TempCount1 = 0, TempCount2 = 0, TempCount3 = 0;

		for (int i = 0; i < aV.length; i++) {
			Temp1 += aV[i];
			Temp2 += bV[i];
		}

		if (Temp1 == 0 || Temp2 == 0)
			result = 100;
		if (Temp1 == 0 && Temp2 == 0)
			result = 0;

		if (Temp1 > 0 && Temp2 > 0) {
			for (int i = 0; i < aV.length; i++) {
				TempCount1 += (aV[i] / Temp1) * (bV[i] / Temp2);
				TempCount2 += (bV[i] / Temp2) * (bV[i] / Temp2);
				TempCount3 += (aV[i] / Temp1) * (aV[i] / Temp1);

			}
			result = (100 - 100 * (TempCount1 / (TempCount2 + TempCount3 - TempCount1)));
		}
		
		return (float) result;
	}
	static int num =0;
	private double[] getStringRepresentation(String s) { // added by mlux
		s = s.substring(10).replaceAll(" ", "") ;
		double[] data = new double[s.length()];
		for (int i = 0; i < s.length(); i++) {
			data[i] = (int)s.charAt(i)-48;
		}
		return data;
	}
}
