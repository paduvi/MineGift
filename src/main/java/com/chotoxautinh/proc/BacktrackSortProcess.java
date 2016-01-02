package com.chotoxautinh.proc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chotoxautinh.model.ClazzWrapper;
import com.chotoxautinh.model.Subject;

public class BacktrackSortProcess {

	private List<ClazzWrapper> resultList = new ArrayList<>();

	public boolean proc(Map<Subject, List<ClazzWrapper>> map) {
		Iterator<Map.Entry<Subject, List<ClazzWrapper>>> iterator = map.entrySet().iterator();
		if (iterator.hasNext())
			return process(iterator);
		return false;
	}

	private boolean process(Iterator<Map.Entry<Subject, List<ClazzWrapper>>> it) {
		Map.Entry<Subject, List<ClazzWrapper>> entry = it.next();
		LOOP: for (ClazzWrapper wrapper : entry.getValue()) {
			if (wrapper.isFull())
				continue LOOP;
			for (ClazzWrapper result : resultList) {
				if (wrapper.conflict(result))
					continue LOOP;
			}
			resultList.add(wrapper);
			if (it.hasNext()) {
				Iterator<Entry<Subject, List<ClazzWrapper>>> temp = it;
				if (process(temp) == true)
					return true;
			} else {
				return true;
			}
			resultList.remove(resultList.size() - 1);
		}
		return false;
	}

	public List<ClazzWrapper> getResultList() {
		return resultList;
	}
}
