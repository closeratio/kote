/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.closeratio.kote

import com.ccfraser.muirwik.components.input.mInput
import com.ccfraser.muirwik.components.mAppBar
import com.ccfraser.muirwik.components.mButton
import com.ccfraser.muirwik.components.mTypography
import react.RBuilder
import react.RComponent

class TodoList: RComponent<TodoProps, TodoState>() {

	override fun RBuilder.render() {
		mAppBar {
			mTypography("Kote")
		}
		mInput(placeholder = "Task") {
		}
		mButton("Add task") {
		}
	}

}
