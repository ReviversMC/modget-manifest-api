package com.github.nebelnidas.modget.manifest_api.spec3.api.util;

import java.util.List;

public abstract class RepoHandlingUtilsBase {

	protected int findMaxSharedInt(List<Integer> intList1, List<Integer> intList2) {
		for (int a : intList1) {
			for (int b : intList2) {
				if (a == b) {
					return a;
				}
			}
		}
		return -1;
	}

	protected int findMaxSharedInt(int a, List<Integer> intList1) {
		for (int b : intList1) {
			if (a == b) {
				return a;
			}
		}
		return -1;
	}

}
