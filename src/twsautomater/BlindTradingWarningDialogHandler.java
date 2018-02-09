// This file is part of TwsAutomater.
// Copyright (C) 2004 Steven M. Kearns (skearns23@yahoo.com )
// Copyright (C) 2004 - 2018 Richard L King (rlking@aultan.com)
// For conditions of distribution and use, see copyright notice in COPYING.txt

// TwsAutomater is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// TwsAutomater is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with TwsAutomater.  If not, see <http://www.gnu.org/licenses/>.

package twsautomater;

import java.awt.Window;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;

class BlindTradingWarningDialogHandler implements WindowHandler {
    public boolean filterEvent(Window window, int eventId) {
        switch (eventId) {
            case WindowEvent.WINDOW_OPENED:
                return true;
            default:
                return false;
        }
    }

    public void handleWindow(Window window, int eventID) {
        if (! Settings.settings().getBoolean("AllowBlindTrading", false)) return;

        if (! SwingUtils.clickButton(window, "Yes")) {
            Utils.logError("could not dismiss blind trading warning dialog.");
        }
    }

    public boolean recogniseWindow(Window window) {
        if (! (window instanceof JDialog)) return false;
        return (SwingUtils.findLabel(window, "blind trading") != null &&
                SwingUtils.findLabel(window, "Are you sure you want to submit this order?") != null);
    }

}
