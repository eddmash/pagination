package com.eddmash.validation.renderer;
/*
* This file is part of the Tools package.
* 
* (c) Eddilbert Macharia (http://eddmash.com)<edd.cowan@gmail.com>
*
* For the full copyright and license information, please view the LICENSE
* file that was distributed with this source code.
*/

import android.view.ViewGroup;

/**
 * Class that implement this interface make it easy to render validation errors
 * {@link ErrorRenderer}
 */
interface RendererInterface {
    void render(ViewGroup errorSpace);
}
