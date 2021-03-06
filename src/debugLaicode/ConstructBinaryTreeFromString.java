package debugLaicode;

public class ConstructBinaryTreeFromString {
    public TreeNode str2tree(String s) {
        // sanity check
        if (s == null || s.length() == 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("(" + s + ")");
        return helper(sb.toString());
    }

    int preIndex = 0;

    // return TreeNode, passing down e.g. (3(11)(111))
    private TreeNode helper(String s) {
        // base-case
        if(s.charAt(preIndex) == ')') return null;

        // recursive rule
        preIndex++;
        // preIndex is first ( or )
        // index is first ) OR (
        // if ) → root has no left child AND has no right child
        // if ( → traverse root.left

        TreeNode root = getRoot(s);
        root.left = helper(s);
        root.right = helper(s);

        preIndex++; // skip last )

        return root;
    }

    private TreeNode getRoot(String newString) {
        boolean isNegative = false;
        if (newString.charAt(preIndex) == '-') {
            isNegative = true;
            preIndex++;
        }
        int tmpt = preIndex;
        while (Character.isDigit(newString.charAt(preIndex))) {
            preIndex++;
        }
        int num = Integer.parseInt(newString.substring(tmpt, preIndex));
        if (isNegative) {
            num = - num;
        }
        return new TreeNode(num);
    }

}