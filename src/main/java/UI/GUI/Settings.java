package UI.GUI;

import UI.GUI.GUILoader.Resolution;

public record Settings(boolean fullscreen, Resolution resolution, double musicVolume, double sfxVolume) {}
