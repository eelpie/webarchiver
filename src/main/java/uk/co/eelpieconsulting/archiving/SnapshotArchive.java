package uk.co.eelpieconsulting.archiving;

public interface SnapshotArchive {

	Snapshot getLatestFor(String url);
	void put(Snapshot snapshot);

}
