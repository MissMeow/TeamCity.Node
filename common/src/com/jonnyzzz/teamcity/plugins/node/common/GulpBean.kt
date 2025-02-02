/*
 * Copyright 2013-2015 Eugene Petrenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jonnyzzz.teamcity.plugins.node.common


public class GulpBean {
  public val gulpConfigurationParameter: String = "gulp"

  public val runTypeName: String = "jonnyzzz.gulp"
  public val file: String = "jonnyzzz.gulp.file"
  public val targets: String = "jonnyzzz.gulp.tasks"
  public val commandLineParameterKey : String = "jonnyzzz.commandLine"
  public val gulpMode : String = "jonnyzzz.gulp.mode"
  public val gulpModeDefault : GulpExecutionMode = GulpExecutionMode.NPM

  public val gulpModes : List<GulpExecutionMode>
    get() = arrayListOf(*GulpExecutionMode.values)

  public fun parseMode(text : String?) : GulpExecutionMode?
          = gulpModes.firstOrNull { text == it.value } ?: gulpModeDefault

  public fun parseCommands(text: String?): Collection<String> {
    if (text == null)
      return listOf()
    else
      return text
              .split("[\r\n]+")
              .map { it.trim() }
              .filterNot { it.isEmpty() }
  }
}

public enum class GulpExecutionMode(val title : String,
                                     val value : String) {
  NPM("NPM package from project", "npm"),
  GLOBAL("System-wide gulp", "global"),
}
