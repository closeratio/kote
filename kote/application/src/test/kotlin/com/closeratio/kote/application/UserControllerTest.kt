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

package com.closeratio.kote.application

import com.closeratio.kote.application.beans.AddUserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
open class UserControllerTest {

	@Autowired
	lateinit var userController: UserController

	@Autowired
	lateinit var mvc: MockMvc

	@Test
	fun contextLoads() {
		assertThat(userController, Matchers.notNullValue())
	}

	@Test
	fun addUser() {
		val response = mvc.perform(post("/users/add")
				.content(ObjectMapper().writeValueAsString(AddUserRequest().apply {
					username = "ivy1"
					displayName = "Ivy"
					emailAddress = "ivy@ivy.com"
				}))
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk)
				.andReturn().response

		assertThat(response.contentLength, Matchers.greaterThan(0))
	}

}