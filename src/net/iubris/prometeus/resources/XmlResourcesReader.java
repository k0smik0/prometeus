/*******************************************************************************
 * Copyleft 2011 Massimiliano Leone - k0smik0@logorroici.org .
 * 
 * XmlResourcesReader.java is part of Prometeus.
 * 
 * Prometeus is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Prometeus is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Prometeus ; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA
 ******************************************************************************/
package net.iubris.prometeus.resources;

import org.simpleframework.xml.Serializer;

import com.google.inject.Inject;

import net.iubris.prometeus.base.XmlReader;
import net.iubris.prometeus.base.exceptions.XmlReaderParsingException;
import net.iubris.prometeus.resources.exceptions.XmResourceReaderResourcesNotFoundException;


import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
/**
 * Provides a mapper xml-class for xml file within /res/raw directory
 * 
 * This is a auto-injected building, by (robo)guice
 * 
 * @author k0smik0
 *
 * @param <C>
 */
public class XmlResourcesReader<C> extends XmlReader<C> {

	private final Resources resources;

	@Inject
	public XmlResourcesReader(Serializer serializer, Resources resources) {
		super(serializer);
		this.resources = resources;
	}
	
	/**
	 * Return an instance of Class<Parameter> specified from an raw resource xml file 
	 * 
	 * @param resourceId resource within /res/raw which you want parse 
	 * @param clazz Class to instance from parsed resource
	 */
	public C parse(int resourceId, Class<C> clazz) throws XmResourceReaderResourcesNotFoundException, XmlReaderParsingException {
		try {
			return super.parse( resources.openRawResource(resourceId), clazz);
		} catch (NotFoundException e) {			
			e.printStackTrace();
			throw new XmResourceReaderResourcesNotFoundException(resources.getResourceName(resourceId) + " not found in /raw");
		}
	}
}
