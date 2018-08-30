package com.impetus.rest.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.test.context.ActiveProfilesResolver;

public class IntegrationTestProfileResolver implements ActiveProfilesResolver {

	@Override
	public String[] resolve(Class<?> testClass) {
		Path gitDir = getGitDirectory();
		return isMaster(gitDir) ? MASTER : BRANCH;
	}

	private Path getGitDirectory() {
		Path result = null;
		String dir = System.getProperty("project.git.dir");
		if (!StringUtils.isBlank(dir)) {
			Path dirp = Paths.get(dir);
			if (Files.isDirectory(dirp)) {
				result = dirp;
			}
		}
		if (result == null) {
			dir = System.getProperty("user.dir");
			if (!StringUtils.isBlank(dir)) {
				Path dirp = Paths.get(dir + "/../../.git");
				if (Files.isDirectory(dirp)) {
					result = dirp;
				}
				if (result == null) {
					dirp = Paths.get(dir + "/../.git");
					if (Files.isDirectory(dirp)) {
						result = dirp;
					}
				}
			}
		}

		if (result == null) {
			throw new RuntimeException("project's git director must be speified by the property [project.git.dir]");
		}

		return result;
	}

	private boolean isMaster(Path gitDir) {
		boolean result = false;
		Path headFilePath = gitDir.resolve("HEAD");
		try {
			List<String> lines = Files.readAllLines(headFilePath);
			if (lines.isEmpty()) {
				throw new RuntimeException("file " + headFilePath.toString() + " is empty");
			}
			if (lines.get(0).contains("refs/heads/master")) {
				result = true;
			}
		} catch (IOException e) {
			throw new RuntimeException("unable to read file [" + headFilePath.toString() + "]", e);
		}
		return result;
	}

	private static final String[] MASTER = new String[] { "master" };
	private static final String[] BRANCH = new String[] { "master", "branch" };
}
