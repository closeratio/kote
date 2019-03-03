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

package com.closeratio.kote.model

class Lobby(
		val name: String,
		val description: String
) {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other == null || this::class.js != other::class.js) return false

		other as Lobby

		if (name != other.name) return false
		if (description != other.description) return false

		return true
	}

	override fun hashCode(): Int {
		var result = name.hashCode()
		result = 31 * result + description.hashCode()
		return result
	}

	override fun toString(): String {
		return "Lobby(name='$name', description='$description')"
	}

}
