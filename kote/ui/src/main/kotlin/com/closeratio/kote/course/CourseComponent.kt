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

import com.ccfraser.muirwik.components.MButtonSize.small
import com.ccfraser.muirwik.components.MColor.primary
import com.ccfraser.muirwik.components.MTypographyVariant.h5
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardActions
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardMedia
import com.ccfraser.muirwik.components.mButton
import com.ccfraser.muirwik.components.mTypography
import kotlinx.css.pct
import kotlinx.css.px
import react.RBuilder
import react.RComponent
import react.RState
import styled.css
import styled.styledDiv

class CourseComponent: RComponent<CourseComponentProps, RState>() {

	override fun RBuilder.render() {
		styledDiv {
			mCard {
				mCardMedia(
						image = "kotlin_logo.png",
						title = props.course.name) {
					css {
						height = 0.px
						paddingTop = 56.25.pct
					}
				}

				mCardContent {
					mTypography(
							props.course.name,
							gutterBottom = true,
							variant = h5,
							component = "h2")
					mTypography(
							props.course.description,
							component = "p")
				}

				mCardActions {
					mButton(
							"Go to course",
							size = small,
							color = primary,
							href = "http://www.${props.course.name}.com",
							target = "blank")
				}
			}
		}
	}
}

fun RBuilder.courseComponent(props: CourseComponentProps) = child(CourseComponent::class) {
	attrs.course = props.course
}