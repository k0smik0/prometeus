/**
 * Massimiliano Leone - k0smik0@logorroici.org
 * 
 * 
 * 
 * LGPL license
 */
package net.iubris.prometeus.assets;

import java.io.IOException;
import java.io.InputStream;

import net.iubris.prometeus.assets.exceptions.XmlAssetReaderFileNotFoundException;
import net.iubris.prometeus.base.XmlReader;
import net.iubris.prometeus.base.exceptions.XmlReaderParsingException;

import org.simpleframework.xml.Serializer;

import android.content.res.AssetManager;

import com.google.inject.Inject;
/**
 * Provides a mapper xml-class for xml file within /asset directory
 * 
 * This is a auto-injected building, by (robo)guice
 * 
 * @author k0smik0
 *
 * @param <C> 
 */
public class XmlAssetReader<C> extends XmlReader<C> {

	private AssetManager assetManager;
	
	@Inject
	public XmlAssetReader(Serializer serializer, AssetManager assetManager) {
		super(serializer);
		this.assetManager = assetManager;
	}	
	
	/**
	 * Return an instance of Class<Parameter> specified from a xml file within /asset
	 * 
	 * @param fileName File Name within /asset which you want parse 
	 * @param clazz Class to instance from parsed file
	 * @return an instance of Class<Parameter>
	 */
	public C parse(String fileName, Class<C> clazz) throws XmlAssetReaderFileNotFoundException, XmlReaderParsingException {
		InputStream inputStream;
		try {
			inputStream = assetManager.open(fileName);
			return super.parse(inputStream, clazz);
		} catch (IOException e) {
			throw new XmlAssetReaderFileNotFoundException(fileName + " not found in /assets");
		}	
	}		
}