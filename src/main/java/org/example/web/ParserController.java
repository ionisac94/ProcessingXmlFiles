package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.service.ParsingXml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ParserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParserController.class);

	private final ParsingXml parser;

	@GetMapping(path = "/cache/clear", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> clearCache() {
		LOGGER.info("Clearing and reloading cache");
		parser.parsingXML();
		LOGGER.info("Cache cleared and reloaded");

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
