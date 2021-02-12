import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import { ViewEncapsulation } from '@angular/core';
import { GlobalVariables } from '../../globalVariable';
import { Router } from '@angular/router';
import { AppserviceService } from 'src/app/appservice.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-familytree',
  templateUrl: './familytree.component.html',
  styleUrls: ['./familytree.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class FamilytreeComponent implements AfterViewInit {
  @ViewChild('treeEle') tree: any;

  data: any = [];
  elements = [];
  root = null;
  levels = [];
  // tree: ElementRef
  levelMap = [];
  gap = 32;
  size = 64;
  startTop = 0;
  startLeft = 0;
  userDetails: any;

  constructor(
    public route: ActivatedRoute,
    private _elementRef: ElementRef,
    private router: Router,
    private addService: AppserviceService
  ) {
    this.startTop = window.innerHeight / 3 - this.size / 2;
    this.startLeft = window.innerWidth / 3 - this.size / 2;
    this.userDetails = GlobalVariables.userDetails;
    console.log(this.userDetails);
  }

  ngAfterViewInit(): void {
    this.addService
      .familytree(
        this.userDetails.username,
        this.route.snapshot.queryParamMap.get('id')
      )
      .subscribe(
        (res: any) => {
          this.data = res;
          this.data = this.data.map((ele: any) => {
            let user = {
              ...ele.u1,
              id: ele.u1.username,
              partners: [ele.u1.spouse_username].filter(Boolean),
              parents: [ele.u1.mom_username, ele.u1.dad_username].filter(
                Boolean
              ),
            };
            return {
              ...user,
              level: ele.k,
              root: ele.root,
            };
          });
          console.log(this.data);
          console.log(this.tree);
          this.tree = this.tree.nativeElement;
          this.data.forEach((elem: any) => {
            // @ts-ignore
            if (this.levels.indexOf(elem.level) < 0) {
              // @ts-ignore
              this.levels.push(elem.level);
            }
          });
          this.levels.sort(function (a, b) {
            return a - b;
          });

          this.levels.forEach((level) => {
            var startAt = this.data.filter((person: any) => {
              return person.level == level;
            });
            startAt.forEach((start: any) => {
              var person = this.findPerson(start.id);
              // @ts-ignore
              this.plotNode(person, 'self');
              this.plotParents(person);
            });
          });
          this.adjustNegatives();
        },
        (error: any) => {
          console.log(error);
        }
      );
  }

  plotParents(start: any) {
    if (!start) {
      return;
    }
    start.parents.reduce((previousId: any, currentId: any) => {
      var previousParent = this.findPerson(previousId),
        currentParent = this.findPerson(currentId);
      if (currentParent == undefined) {
        return;
      }
      // @ts-ignore
      this.plotNode(currentParent, 'parents', start, start.parents.length);
      if (previousParent) {
        this.plotConnector(previousParent, currentParent, 'partners');
      }
      this.plotConnector(start, currentParent, 'parents');
      this.plotParents(currentParent);
      return currentId;
    }, 0);
  }

  plotNode() {
    var person = arguments[0],
      personType = arguments[1],
      relative = arguments[2],
      numberOfParents = arguments[3],
      node = this.get(person.id),
      relativeNode,
      element: any = {},
      thisLevel,
      exists;
    if (node) {
      return;
    }
    node = document.createElement('div');
    node.id = person.id;
    node.classList.add('node');
    node.classList.add('asset');
    node.textContent = person.name;
    node.setAttribute('data-level', person.level);

    thisLevel = this.findLevel(person.level);
    if (!thisLevel) {
      thisLevel = { level: person.level, top: this.startTop };
      // @ts-ignore
      this.levelMap.push(thisLevel);
    }

    if (personType == 'self') {
      node.style.left = this.startLeft + 'px';
      node.style.top = thisLevel.top + 'px';
    } else {
      relativeNode = this.get(relative.id);
    }
    if (personType == 'partners' && relativeNode) {
      node.style.left =
        parseInt(relativeNode.style.left) + this.size + this.gap * 2 + 'px';
      node.style.top = parseInt(relativeNode.style.top) + 'px';
    }
    if (personType == 'children' && relativeNode) {
      node.style.left = parseInt(relativeNode.style.left) - this.size + 'px';
      node.style.top =
        parseInt(relativeNode.style.top) + this.size + this.gap + 'px';
    }
    if (personType == 'parents' && relativeNode) {
      if (numberOfParents == 1) {
        node.style.left = parseInt(relativeNode.style.left) + 'px';
        node.style.top =
          parseInt(relativeNode.style.top) - this.gap - this.size + 'px';
      } else {
        node.style.left = parseInt(relativeNode.style.left) - this.size + 'px';
        node.style.top =
          parseInt(relativeNode.style.top) - this.gap - this.size + 'px';
      }
    }
    while ((exists = this.detectCollision(node))) {
      // @ts-ignore
      node.style.left = exists.left + this.size + this.gap * 2 + 'px';
    }

    if (thisLevel.top > parseInt(node.style.top)) {
      this.updateLevel(person.level, 'top', parseInt(node.style.top));
    }
    element.id = node.id;
    element.left = parseInt(node.style.left);
    element.top = parseInt(node.style.top);
    // @ts-ignore
    this.elements.push(element);
    // @ts-ignore
    this.tree.appendChild(node);
  }

  /* Helper Functions */
  get(id: string) {
    return document.getElementById(id);
  }

  findPerson(id: any) {
    var element = this.data.filter((elem: any) => {
      return elem.id == id;
    });
    return element.pop();
  }

  findLevel(level: any) {
    var element = this.levelMap.filter((elem: any) => {
      return elem.level == level;
    });
    return element.pop();
  }

  updateLevel(id: any, key: string, value: number) {
    this.levelMap.forEach((level: any) => {
      if (level.level === id) {
        level[key] = value;
      }
    });
  }

  detectCollision(node: HTMLElement) {
    var element = this.elements.filter((elem: any) => {
      var left = parseInt(node.style.left);
      return (
        (elem.left == left ||
          (elem.left < left && left < elem.left + this.size + this.gap)) &&
        elem.top == parseInt(node.style.top)
      );
    });
    return element.pop();
  }

  adjustNegatives() {
    var allNodes = document.querySelectorAll('div.asset'),
      minTop = this.startTop,
      diff = 0;
    for (var i = 0; i < allNodes.length; i++) {
      // @ts-ignore
      if (parseInt(allNodes[i].style.top) < minTop) {
        // @ts-ignore
        minTop = parseInt(allNodes[i].style.top);
      }
    }
    if (minTop < this.startTop) {
      diff = Math.abs(minTop) + this.gap;
      for (var i = 0; i < allNodes.length; i++) {
        // @ts-ignore
        allNodes[i].style.top = parseInt(allNodes[i].style.top) + diff + 'px';
      }
    }
  }

  plotConnector(source: any, destination: any, relation: any) {
    var connector = document.createElement('div'),
      orientation,
      comboId,
      comboIdInverse,
      start,
      stop,
      x1,
      y1,
      x2,
      y2,
      length,
      angle,
      transform;
    // We do not plot a connector if already present
    comboId = source.id + '-' + destination.id;
    comboIdInverse = destination.id + '-' + source.id;
    if (document.getElementById(comboId)) {
      return;
    }
    if (document.getElementById(comboIdInverse)) {
      return;
    }

    connector.id = comboId;
    orientation = relation == 'partners' ? 'h' : 'v';
    connector.classList.add('asset');
    connector.classList.add('connector');
    connector.classList.add(orientation);
    start = this.get(source.id);
    stop = this.get(destination.id);
    if (relation == 'partners') {
      // @ts-ignore
      x1 = parseInt(start.style.left) + this.size;
      // @ts-ignore
      y1 = parseInt(start.style.top) + this.size / 2;
      // @ts-ignore
      x2 = parseInt(stop.style.left);
      // @ts-ignore
      y2 = parseInt(stop.style.top);
      length = x2 - x1 + 'px';

      connector.style.width = length;
      connector.style.left = x1 + 'px';
      connector.style.top = y1 + 'px';
      // Avoid collision moving down
      // @ts-ignore
      let exists = this.detectConnectorCollision(connector);
      while (exists) {
        // @ts-ignore
        connector.style.top = parseInt(exists.style.top) + 4 + 'px';
      }
    }
    if (relation == 'parents') {
      // @ts-ignore
      x1 = parseInt(start.style.left) + this.size / 2;
      // @ts-ignore
      y1 = parseInt(start.style.top);
      // @ts-ignore
      x2 = parseInt(stop.style.left) + this.size / 2;
      // @ts-ignore
      y2 = parseInt(stop.style.top) + (this.size - 2);

      length = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
      angle = (Math.atan2(y2 - y1, x2 - x1) * 180) / Math.PI;
      transform = 'rotate(' + angle + 'deg)';

      connector.style.width = length + 'px';
      connector.style.left = x1 + 'px';
      connector.style.top = y1 + 'px';
      connector.style.transform = transform;
    }
    // @ts-ignore
    this.tree.appendChild(connector);
  }

  detectConnectorCollision(connector: HTMLDivElement) {
    var connectors = [].slice.call(
      document.querySelectorAll('div.connector.h')
    );
    var element = connectors.filter(function (elem) {
      return (
        // @ts-ignore
        elem.style.left == connector.style.left &&
        // @ts-ignore
        elem.style.top == connector.style.top
      );
    });
    return element.pop();
  }

  dashboard() {
    this.router.navigate(['dashboard']);
  }
}
