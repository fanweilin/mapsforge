/*
 * Copyright 2010, 2011, 2012 mapsforge.org
 *
 * This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.mapsforge.map.layer.overlay;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.mapsforge.core.graphics.AwtGraphicFactory;
import org.mapsforge.core.graphics.Canvas;
import org.mapsforge.core.graphics.GraphicFactory;
import org.mapsforge.core.graphics.Paint;
import org.mapsforge.core.model.BoundingBox;
import org.mapsforge.core.model.GeoPoint;
import org.mapsforge.core.model.Point;
import org.mapsforge.core.model.Tile;

public class PolygonTest {
	private static final GraphicFactory GRAPHIC_FACTORY = AwtGraphicFactory.INSTANCE;

	@Test
	public void constructorTest() {
		Paint paintFill = GRAPHIC_FACTORY.createPaint();
		Paint paintStroke = GRAPHIC_FACTORY.createPaint();

		Polygon polygon = new Polygon(paintFill, paintStroke, GRAPHIC_FACTORY);
		Assert.assertTrue(polygon.getGeoPoints().isEmpty());
		Assert.assertEquals(paintFill, polygon.getPaintFill());
		Assert.assertEquals(paintStroke, polygon.getPaintStroke());
	}

	@Test
	public void drawTest() {
		Polygon polygon = new Polygon(null, null, GRAPHIC_FACTORY);

		BoundingBox boundingBox = new BoundingBox(-1, -1, 1, 1);
		Canvas canvas = GRAPHIC_FACTORY.createCanvas();
		canvas.setBitmap(GRAPHIC_FACTORY.createBitmap(Tile.TILE_SIZE, Tile.TILE_SIZE));
		Point point = new Point(0, 0);
		polygon.draw(boundingBox, (byte) 0, canvas, point);

		polygon.getGeoPoints().add(new GeoPoint(0, 0));
		polygon.getGeoPoints().add(new GeoPoint(1, 1));
		polygon.draw(boundingBox, (byte) 0, canvas, point);

		polygon.setPaintFill(GRAPHIC_FACTORY.createPaint());
		polygon.setPaintStroke(GRAPHIC_FACTORY.createPaint());
		polygon.draw(boundingBox, (byte) 0, canvas, point);
	}

	@Test
	public void setterTest() {
		GeoPoint geoPoint = new GeoPoint(0, 0);
		Paint paintFill = GRAPHIC_FACTORY.createPaint();
		Paint paintStroke = GRAPHIC_FACTORY.createPaint();

		Polygon polygon = new Polygon(null, null, GRAPHIC_FACTORY);
		Assert.assertTrue(polygon.getGeoPoints().isEmpty());
		Assert.assertNull(polygon.getPaintFill());
		Assert.assertNull(polygon.getPaintStroke());

		polygon.getGeoPoints().add(geoPoint);
		Assert.assertEquals(Arrays.asList(geoPoint), polygon.getGeoPoints());

		polygon.setPaintFill(paintFill);
		polygon.setPaintStroke(paintStroke);
		Assert.assertEquals(paintFill, polygon.getPaintFill());
		Assert.assertEquals(paintStroke, polygon.getPaintStroke());
	}
}
