package net.semanticmetadata.lire;

import org.apache.lucene.document.Document;
/*
 * This file is part of the Caliph and Emir project: http://www.SemanticMetadata.net.
 *
 * Caliph & Emir is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Caliph & Emir is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Caliph & Emir; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Copyright statement:
 * --------------------
 * (c) 2002-2006 by Mathias Lux (mathias@juggle.at)
 * http://www.juggle.at, http://www.SemanticMetadata.net
 */

/**
 * This class simulates the original Lucene Hits object. 
 * Please note the only a certain number of results are returned.<br>
 *
 * This file is part of the Caliph and Emir project: http://www.SemanticMetadata.net
 * <br>Date: 02.02.2006
 * <br>Time: 23:45:20
 *
 * @author Mathias Lux, mathias@juggle.at
 */
public interface ImageSearchHits {
    /**
     * Returns the size of the result list.
     * @return the size of the result list.
     */
    public int length();

    /**
     * Returns the score of the document at given position.
     * Please note that the score in this case is a distance,
     * which means a score of 0 denotes the best possible hit.
     * The result list starts with position 0 as everything
     * in computer science does.
     * @param position defines the position
     * @return the score of the document at given position. The lower the better (its a distance measure).
     */
    public float score(int position);

    /**
     *Returns the document at given position
     * @param position defines the position.
     * @return the document at given position.
     */
    public Document doc(int position);
}
