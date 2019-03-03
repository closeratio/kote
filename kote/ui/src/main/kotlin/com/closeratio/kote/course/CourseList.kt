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

package com.closeratio.kote.course

import com.ccfraser.muirwik.components.MGridSize.*
import com.ccfraser.muirwik.components.MGridSpacing.spacing24
import com.ccfraser.muirwik.components.MTextFieldMargin.normal
import com.ccfraser.muirwik.components.mGridContainer
import com.ccfraser.muirwik.components.mGridItem
import com.ccfraser.muirwik.components.mTextField
import com.closeratio.kote.setStateWithCallback
import kotlinx.css.padding
import kotlinx.css.px
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.setState
import styled.css
import styled.styledDiv

class CourseList : RComponent<RProps, CourseListState>() {
	override fun CourseListState.init() {
		courses = arrayListOf()
		searchString = ""
	}

	override fun componentDidMount() {
		getCourses()
	}

	private fun getCourses() {
		val allCourses = arrayListOf(
				Course("History", "Learn about old stuff"),
				Course("Geography", "Learn where things are"),
				Course("Maths", "Learn how to add stuff up"),
				Course("Science", "Learn how things work"))

		val filtered = allCourses.filter { it.name.toLowerCase().contains(state.searchString.toLowerCase()) }

		setState {
			courses = filtered
		}
	}

	private fun onSearchInputChange(event: Event) {

		val newSearchString = (event.target as HTMLInputElement).value

		setStateWithCallback({
			searchString = newSearchString
		}, {
			getCourses()
		})
	}

	override fun RBuilder.render() {
		styledDiv {
			mTextField(
					label = "",
					id = "searchInput",
					placeholder = "Search for Courses",
					margin = normal,
					onChange = { onSearchInputChange(it) }) {
				css {
					padding(24.px)
				}
			}

			mGridContainer(
					spacing = spacing24) {
				css {
					padding(24.px)
				}

				state.courses.forEach {
					mGridItem(
							xs = Cells12,
							sm = Cells6,
							lg = Cells4,
							xl = Cells3) {

						courseComponent(CourseComponentProps(it))

					}
				}
			}
		}
	}
}

fun RBuilder.courseList() = child(CourseList::class) {}