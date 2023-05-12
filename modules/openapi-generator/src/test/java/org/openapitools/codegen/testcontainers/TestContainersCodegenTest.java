package org.openapitools.codegen.testcontainers;

import org.openapitools.codegen.DefaultGenerator;
import org.openapitools.codegen.TestUtils;
import org.openapitools.codegen.config.CodegenConfigurator;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestContainersCodegenTest {

	@Test
	public void verifyGoMod() throws IOException {
		File output = Files.createTempDirectory("test").toFile();
		output.deleteOnExit();

		final CodegenConfigurator configurator = new CodegenConfigurator()
				.setGeneratorName("test-containers")
				.setGitUserId("my-user")
				.setGitRepoId("my-repo")
				.setPackageName("my-package")
				.setInputSpec("src/test/resources/3_0/test-containers/sample.yaml")
				.setOutputDir(output.getAbsolutePath().replace("\\", "/"));

		DefaultGenerator generator = new DefaultGenerator();
		List<File> files = generator.opts(configurator.toClientOptInput()).generate();
		files.forEach(File::deleteOnExit);
		//files.forEach(System.out::println);

		TestUtils.assertFileExists(Paths.get(output + "/go.mod"));
		TestUtils.assertFileContains(Paths.get(output + "/go.mod"),
				"module github.com/my-user/my-repo");
		TestUtils.assertFileContains(Paths.get(output + "/go.mod"),
				"require github.com/gin-gonic/gin v1.9.0");

		log(output + "/api/api_basic.go");
	}

	private void log(String filename) throws IOException {
		System.out.println(new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8));
	}



}
