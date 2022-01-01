package com.github.reviversmc.modget.manifests.spec4.impl.downloaders;

import java.util.List;

public abstract class RepoHandlingUtilsBase {

	protected int findMaxSharedInt(List<Integer> intList1, List<Integer> intList2) {
        int maxSharedInt = -1;
		for (int a : intList1) {
            if (a <= maxSharedInt) continue;

			for (int b : intList2) {
                if (b <= maxSharedInt) continue;
				if (a == b) {
					maxSharedInt = a;
				}
			}
		}
		return maxSharedInt;
	}

	protected int findMaxSharedInt(int a, List<Integer> intList1) {
        int maxSharedInt = -1;

		for (int b : intList1) {
            if (b <= maxSharedInt) continue;
			if (a == b) {
				maxSharedInt = a;
			}
		}
		return maxSharedInt;
	}

}
