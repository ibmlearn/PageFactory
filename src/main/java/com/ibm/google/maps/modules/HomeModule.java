package com.ibm.google.maps.modules;

public class HomeModule {

    public static final String BUTTON_SIGN_IN_XPATH = "//a[text()='Sign in']";
    public static final String BUTTON_EXPAND_COLLAPSE = "//div[contains(@class,'widget-pane-visible')]";
    public static final String BUTTON_WIDGET_PANE_TOGGLE_EXPAND = "//button[contains(@class,'widget-pane-toggle-button') and @aria-label='Expand side panel']";
    public static final String BUTTON_WIDGET_PANE_TOGGLE_COLLAPSE = "//button[contains(@class,'widget-pane-toggle-button') and @aria-label='Collapse side panel']";
    public static final String SEARCH_BOX = "//input[@id='searchboxinput']";
    public static final String SEARCH_BUTTON = "//button[@id='searchbox-searchbutton']";
    public static final String HEADER_TITLE_TEXT = "//h1[contains(@class,'section-hero-header-title')]";
    public static final String ICON_TO_DIAPLAY_SATELLITE_OR_MAP_IMAGE = "//div[contains(@class,'widget-minimap-clip')]";
    public static final String LABEL_TO_DISPLAY_SATELLITE_OR_MAP_IMAGE = "//div[contains(@class,'widget-minimap-clip')]//following-sibling::label";
    public static final String BUTTON_ZOOM_IN = "//button[@id='widget-zoom-in']";
    public static final String BUTTON_ZOOM_OUT = "//button[@id='widget-zoom-out']";
    public static final String BUTTON_RIPPLE = "//div[@class='button-ripple']";
    
}
