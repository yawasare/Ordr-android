package productivity.yaw.asare.ordr;

/**
 * Created by yaw on 12/28/15.
 */
public class Constant {

    public static final String PRIORITY_LEVEL_ONE = "On Time";
    public static final String PRIORITY_LEVEL_TWO = "Needs Attention";
    public static final String PRIORITY_LEVEL_THREE = "Urgent ";
    public static final String PRIORITY_LEVEL_FOUR = "Running Late";

    public static final String[] PRIORITY_LEVEL_STRINGS = {PRIORITY_LEVEL_ONE, PRIORITY_LEVEL_TWO,
                                                        PRIORITY_LEVEL_THREE, PRIORITY_LEVEL_FOUR};


    public static final int[] PRIORITY_PADDING = {5, 40, 80, 140};
    public static final int[] PRIORITY_TEXT_SIZE = {16,18,23,30};
    public static final int[] PRIORITY_LAYOUT_HEIGHT = {100, 200, 300, 400};

    public static final int[] PRIORITY_BACKGROUNDS = {R.attr.color_one, R.attr.color_two,
                                                      R.attr.color_three, R.attr.color_four};
}
