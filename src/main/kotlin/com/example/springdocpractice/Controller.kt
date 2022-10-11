package com.example.springdocpractice

import com.example.springdocpractice.PracticeExample.REQUEST_A_TYPE_EXAMPLE
import com.example.springdocpractice.PracticeExample.REQUEST_B_TYPE_EXAMPLE
import com.example.springdocpractice.PracticeExample.RESPONSE_A_TYPE_EXAMPLE
import com.example.springdocpractice.PracticeExample.RESPONSE_B_TYPE_EXAMPLE
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class Controller {

    @Operation(
        description = "API 설명",
        requestBody = io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = [Content(
                examples = [
                    ExampleObject(name = "A Type Request Example", summary = "A", value = REQUEST_A_TYPE_EXAMPLE),
                    ExampleObject(name = "B Type Request Example", summary = "B", value = REQUEST_B_TYPE_EXAMPLE),
                ],
            )]
        ),
        responses = [
            ApiResponse(
                responseCode = "200",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = [
                        ExampleObject(name = "A Type Response Example", summary = "A", value = RESPONSE_A_TYPE_EXAMPLE),
                        ExampleObject(name = "B Type Response Example", summary = "B", value = RESPONSE_B_TYPE_EXAMPLE),
                    ],
                    schema = Schema(
                        oneOf = [InheritanceResponseA::class, InheritanceResponseB::class],
                        discriminatorMapping = [
                            DiscriminatorMapping(value = "A Type Response", schema = InheritanceResponseA::class),
                            DiscriminatorMapping(value = "B Type Response", schema = InheritanceResponseB::class),
                        ],
                    )
                )],
            )]
    )
    @PostMapping
    fun post(@RequestBody request: Request): Response {
        return request.toResponse()
    }
}
