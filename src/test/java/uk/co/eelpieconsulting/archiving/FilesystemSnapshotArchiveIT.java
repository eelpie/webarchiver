package uk.co.eelpieconsulting.archiving;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.junit.Test;

public class FilesystemSnapshotArchiveIT {

	private static final String URL = "http://localhost/test/index.html?page=2";

	@Test
	public void canStoreAndRetrieveSnapshot() throws Exception {
		final Date now = DateTime.now().withMillisOfSecond(0).toDate();
		final String testContent = UUID.randomUUID().toString();
		
		final Snapshot snapshot = new Snapshot(URL, now, testContent);		
		FilesystemSnapshotArchive archive = new FilesystemSnapshotArchive(FileUtils.getTempDirectoryPath());
		
		archive.put(snapshot);
		final Snapshot latestSnapshot = archive.getLatestFor(URL);

		assertEquals(URL, latestSnapshot.getUrl());
		assertEquals(testContent, latestSnapshot.getBody());
		assertEquals(new DateTime(now), new DateTime(latestSnapshot.getDate()));
	}
	
}
