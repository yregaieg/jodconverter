/*
 * Copyright 2004 - 2012 Mirko Nasato and contributors
 *           2016 - 2017 Simon Braconnier and contributors
 *
 * This file is part of JODConverter - Java OpenDocument Converter.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jodconverter.cli;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

public class ConvertTest {

  private static final String CONFIG_DIR = "src/integTest/resources/config/";
  private static final String SOURCE_FILE = "src/test/resources/documents/test.doc";
  private static final String OUTPUT_DIR = "test-output/" + ConvertTest.class.getSimpleName();

  @Test
  public void convert() throws Exception {

    final File inputFile = new File(SOURCE_FILE);
    final File outputFile = new File(OUTPUT_DIR, "convert.pdf");

    Convert.main(new String[] {"-k", inputFile.getPath(), outputFile.getPath()});

    assertTrue(outputFile.isFile() && outputFile.length() > 0);
  }

  @Test
  public void convertWithMultipleFilters() throws Exception {

    final File filterChainFile = new File(CONFIG_DIR + "applicationContext_multipleFilters.xml");
    final File inputFile = new File(SOURCE_FILE);
    final File outputFile = new File(OUTPUT_DIR, "convertWithMultipleFilters.pdf");

    Convert.main(
        new String[] {
          "-k", "-o", "-a", filterChainFile.getPath(), inputFile.getPath(), outputFile.getPath()
        });

    assertTrue(outputFile.isFile() && outputFile.length() > 0);
  }

  @Test
  public void convertWithSingleFilter() throws Exception {

    final File filterChainFile = new File(CONFIG_DIR + "applicationContext_singleFilter.xml");
    final File inputFile = new File(SOURCE_FILE);
    final File outputFile = new File(OUTPUT_DIR, "convertWithSingleFilter.pdf");

    Convert.main(
        new String[] {
          "-k", "-o", "-a", filterChainFile.getPath(), inputFile.getPath(), outputFile.getPath()
        });

    assertTrue(outputFile.isFile() && outputFile.length() > 0);
  }
}
