package freenet.client;

import freenet.keys.FreenetURI;
import freenet.support.BucketFactory;

/**
 * Segment of a splitfile, for insertion purposes.
 */
public class InsertSegment {

	final FECCodec codec;
	final SplitfileBlock[] origDataBlocks;
	final int blockLength;
	final BucketFactory bf;
	/** Check blocks. Will be created by encode(...). */
	final SplitfileBlock[] checkBlocks;
	
	public InsertSegment(short splitfileAlgo, SplitfileBlock[] origDataBlocks, int blockLength, BucketFactory bf) {
		this.origDataBlocks = origDataBlocks;
		codec = FECCodec.getCodec(splitfileAlgo, origDataBlocks.length);
		checkBlocks = new SplitfileBlock[codec.countCheckBlocks()];
		this.blockLength = blockLength;
		this.bf = bf;
	}

	/**
	 * Get the check block URIs.
	 * Don't call before encode()! Don't call before all blocks have inserted either.
	 */
	public FreenetURI[] getCheckURIs() {
		FreenetURI[] uris = new FreenetURI[checkBlocks.length];
		for(int i=0;i<uris.length;i++)
			uris[i] = checkBlocks[i].getURI();
		return uris;
	}

	/**
	 * Encode the data blocks into check blocks.
	 * @return The number of check blocks generated.
	 */
	public int encode(int offset) {
		if(codec == null) return 0; // no FEC
		for(int i=0;i<checkBlocks.length;i++)
			checkBlocks[i] = new BlockInserter(null, offset + i);
		codec.encode(origDataBlocks, checkBlocks, blockLength, bf);
		return checkBlocks.length;
	}

}
