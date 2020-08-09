package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.helper.HelperParserXML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ParsingXmlImp implements ParsingXml {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParsingXmlImp.class);

	private final HelperParserXML helperParserXML;

	@Override
	@Scheduled(fixedDelayString = "${refreshRate}")
	public void parsingXML() {
		LOGGER.info("Started parsing Xml file");
		try {
			helperParserXML.parseXml();
			LOGGER.info("Finished parsing Xml file");
		} catch (Exception e) {
			LOGGER.error("Unable to parse Xml file", e);
		}
	}
}
