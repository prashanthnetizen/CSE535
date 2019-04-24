package com.group29.bcardp;

public class ClassifyTree {

    public static boolean result = false;
    private static ClassifyTree tree = null;

    public static ClassifyTree getInstance(){

        if(tree == null){
            tree = new ClassifyTree();
        }

        return tree;
    }

    public static boolean rootTree(double[] feature){
        if(feature[0] < 64.25){
            return true;
        } else if( feature[0] >= 64.25){
            return node3(feature);
        } else {
            return false;
        }
    }

    private static boolean node3(double[] feature){
        if(feature[1] < 187.94){
            return node4(feature);
        } else if( feature[1] >= 187.94){
            return node5(feature);
        } else {
            return false;
        }
    }

    private static boolean node4(double[] feature){
        if(feature[1] < 18.594){
            return node6(feature);
        } else if( feature[1] >= 18.594){
            return node7(feature);
        } else {
            return false;
        }
    }

    private static boolean node5(double[] feature){
        if(feature[1] < 244.72){
            return node8(feature);
        } else if( feature[1] >= 244.72){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node6(double[] feature){
        if(feature[1] < 6.375){
            return false;
        } else if( feature[1] >= 6.375){
            return node11(feature);
        } else {
            return false;
        }
    }

    private static boolean node7(double[] feature){
        if(feature[0] < 98.625){
            return node12(feature);
        } else if( feature[0] >= 98.625){
            return node13(feature);
        } else {
            return false;
        }
    }

    private static boolean node8(double[] feature){
        if(feature[1] < 209.72){
            return true;
        } else if( feature[1] >= 209.72){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node11(double[] feature){
        if(feature[1] < 6.59375){
            return true;
        } else if( feature[1] >= 6.59375){
            return node17(feature);
        } else {
            return false;
        }
    }

    private static boolean node12(double[] feature){
        if(feature[0] < 87.875){
            return node18(feature);
        } else if( feature[0] >= 87.875){
            return node19(feature);
        } else {
            return false;
        }
    }

    private static boolean node13(double[] feature){
        if(feature[1] < 80.719){
            return node20(feature);
        } else if( feature[1] >= 80.719){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node17(double[] feature){
        if(feature[1] < 14.844){
            return false;
        } else if( feature[1] >= 14.844){
            return node23(feature);
        } else {
            return false;
        }
    }

    private static boolean node18(double[] feature){
        if(feature[0] < 67.875){
            return node24(feature);
        } else if( feature[0] >= 67.875){
            return node25(feature);
        } else {
            return false;
        }
    }

    private static boolean node19(double[] feature){
        if(feature[1] < 153.25){
            return node26(feature);
        } else if( feature[1] >= 153.25){
            return false;
        } else {
            return true;
        }
    }


    private static boolean node20(double[] feature){
        if(feature[0] < 164){
            return node28(feature);
        } else if( feature[0] >= 164){
            return false;
        } else {
            return false;
        }
    }


    private static boolean node23(double[] feature){
        if(feature[1] < 15.938){
            return true;
        } else if( feature[1] >= 15.938){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node24(double[] feature){
        if(feature[0] < 66.875){
            return node32(feature);
        } else if( feature[0] >= 66.875){
            return true;
        } else {
            return true;
        }
    }

    private static boolean node25(double[] feature){
        if(feature[1] < 30.938){
            return node34(feature);
        } else if( feature[1] >= 30.938){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node26(double[] feature){
        if(feature[1] < 102.345){
            return true;
        } else if( feature[1] >= 102.345){
            return node37(feature);
        } else {
            return true;
        }
    }

    private static boolean node28(double[] feature){
        if(feature[0] < 129.125){
            return false;
        } else if( feature[0] >= 129.125){
            return true;
        } else {
            return false;
        }
    }

    private static boolean node32(double[] feature){
        if(feature[0] < 65.25){
            return true;
        } else if( feature[0] >= 65.25){
            return false;
        } else {
            return false;
        }
    }

    private static boolean node34(double[] feature){
        if(feature[0] < 81){
            return true;
        } else if( feature[0] >= 81){
            return false;
        } else {
            return true;
        }
    }

    private static boolean node37(double[] feature){
        if(feature[0] < 92.375){
            return false;
        } else if( feature[0] >= 92.375){
            return true;
        } else {
            return true;
        }
    }



}
