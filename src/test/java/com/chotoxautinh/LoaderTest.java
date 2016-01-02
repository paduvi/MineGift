package com.chotoxautinh;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import com.chotoxautinh.model.ClazzWrapper;
import com.chotoxautinh.model.SubClazz;
import com.chotoxautinh.model.Subject;
import com.chotoxautinh.proc.BacktrackSortProcess;
import com.chotoxautinh.util.ClazzLoader;

public class LoaderTest {
	public static void main(String[] args) throws URISyntaxException {
		File folder = new File(LoaderTest.class.getResource("/").toURI());
		File resource = new File(folder, "clazz2.txt");

		Map<Subject, List<ClazzWrapper>> map = ClazzLoader.loadClazzFromPlain(resource);

		BacktrackSortProcess bsp = new BacktrackSortProcess();
		if (bsp.proc(map) == true) {
			for (ClazzWrapper wrapper : bsp.getResultList()) {
				System.out.println("=========================");
				System.out.println(wrapper.getSubject().getName());
				for (SubClazz clazz : wrapper.getSubClazzes()) {
					System.out.println(clazz.getName());
				}
			}
		} else {
			System.out.println("Không ghép được lịch thỏa mãn");
		}
	}
}
