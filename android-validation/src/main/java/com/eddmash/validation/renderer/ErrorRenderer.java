package com.eddmash.validation.renderer;
/*
* This file is part of the Ziamismalawi package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eddmash.validation.ValidatorInterface;

import java.util.List;
import java.util.Map;

/**
 * Renders all errors found in the validator
 */
public class ErrorRenderer implements RendererInterface {

    private Activity context;
    private ValidatorInterface validator;

    public ErrorRenderer(Activity activity, ValidatorInterface validatorInterface) {

        this.context = activity;
        this.validator = validatorInterface;
    }

    @Override
    public void render(ViewGroup errorSpace) {
        TextView errView;
        List tagErrors;
        errorSpace.setVisibility(View.VISIBLE);
        Map<String, List> errors = validator.getErrors();

        for (String errorTag : errors.keySet()) {

            tagErrors = errors.get(errorTag);

            if (tagErrors.size() == 0) {
                continue;
            }

            // set tag identifier
            errView = new TextView(context);
            errView.setText(errorTag);
            errView.setTextColor(Color.WHITE);
            errView.setTextSize(15);
            errView.setTypeface(null, Typeface.BOLD);
            errView.setAllCaps(true);
            errorSpace.addView(errView);

            // render errors
            GridLayout errorsLayout = new GridLayout(context);
            errorsLayout.setColumnCount(3);
            errorsLayout.setRowCount(errors.size());
            LinearLayout.LayoutParams pms = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            errorsLayout.setLayoutParams(pms);
            errorSpace.addView(errorsLayout);

            for (Object error : tagErrors) {
                errView = new TextView(context);
                errView.setText(" + "+error);
                errView.setTextColor(Color.WHITE);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                params.width = GridLayout.LayoutParams.WRAP_CONTENT;
                params.setMargins(2, 2, 2, 2);
                errView.setPadding(2, 2, 2, 2);
                errView.setLayoutParams(params);
                errorsLayout.addView(errView);
            }
        }
    }
}
