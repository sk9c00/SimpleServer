/*
 * Copyright (c) 2010 SimpleServer authors (see CONTRIBUTORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package simpleserver.config.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import simpleserver.Player;

public class PlayerStorage extends Storage {
  private Map<String, PlayerConfig> players = new HashMap<String, PlayerConfig>();

  void add(PlayerConfig player) {
    players.put(player.name, player);
  }

  public void setGroup(String name, int group) {
    name = name.toLowerCase();
    if (contains(name)) {
      players.get(name).group = group;
    } else {
      players.put(name, new PlayerConfig(name, group));
    }
  }

  public void remove(String name) {
    name = name.toLowerCase();
    if (contains(name)) {
      players.remove(name);
    }
  }

  public boolean contains(String name) {
    name = name.toLowerCase();
    return players.containsKey(name);
  }

  public Integer group(String name) {
    name = name.toLowerCase();
    return contains(name) ? players.get(name).group : null;
  }

  public Integer get(Player player) {
    return group(player.getName());
  }

  public int count() {
    return players.size();
  }

  @Override
  Iterator<PlayerConfig> iterator() {
    return players.values().iterator();
  }

  @Override
  void add(XMLTag child) {
    add((PlayerConfig) child);
  }
}
